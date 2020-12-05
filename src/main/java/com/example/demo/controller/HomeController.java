package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Talk;
import com.example.demo.repository.TalkRepository;

@Controller
public class HomeController {

	//リポジトリ利用できるように宣言
	private final TalkRepository talkRepository;
	//ラジオボタンの変数宣言
	private String radioName;

	public HomeController(TalkRepository talkRepository) {
		this.talkRepository = talkRepository;
	}

	public String getRadioName() {
		return radioName;
	}

	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}

	//	ホーム画面の表示
	@RequestMapping("/home")
	public String homeAcc() {
		return "home";
	}

	//ラジタボタンの生成
	private Map<String, String> getRadioItems() {
		Map<String, String> selectMap = new LinkedHashMap<String, String>();
		/* 第1引数は値
		 * 第2引数はHTMLで表示される選択肢 */
		selectMap.put("🍎 #ff0000", "🍎");
		selectMap.put("🐬 #87cefa", "🐬");
		selectMap.put("🌱 #7cfc00", "🌱");
		selectMap.put("🐥 #ffff00", "🐥");
		selectMap.put("🎀 #ff1493", "🎀");
		selectMap.put("⛄️ #ffffff", "⛄️");
		return selectMap;
	}

	//	タイムラインページの表示
	@GetMapping("/timeLine")
	public String radioTimeLine(@ModelAttribute Talk talk, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		model.addAttribute("radioItems", getRadioItems());
		return "timeLine";
	}

	/*	コメント登録とバリデーションチェック
		BindingResultはBeanValidationの機能(インターフェースらしい)	*/
	@PostMapping("/add")
	public String addComment(@Validated @ModelAttribute Talk talk, BindingResult result, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		if (result.hasErrors()) {
			return "redirect:/timeLine";
		}
		//日付取得
		talk.setNowdate(LocalDateTime.now());
		//コメントの登録
		talkRepository.save(talk);
		return "redirect:/timeLine";
	}
}
