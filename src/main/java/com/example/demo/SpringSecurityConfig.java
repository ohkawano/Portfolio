package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//SpringSecurityの設定
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	//パスワードのエンコード方式を変更
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//DBを使うので宣言
	@Autowired
	private DataSource dataSource;

	//管理者アカウントの検索クエリ
	private static final String UserSQL = "SELECT"
			+ " username,"
			+ " authority,"
			+ " true"
			+ " FROM"
			+ " authorities"
			+ " WHERE"
			+ " username=?";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* アクセス設定
		 * .permitAll()がついているものは全員がアクセス可能
		 * それ以外は.anyRequest().authenticated();でアクセス禁止*/
		http.authorizeRequests()
				.antMatchers("/home").permitAll()
				.antMatchers("/timeLine").permitAll()
				.antMatchers("/add").permitAll()
				.anyRequest().authenticated();
		//ログイン時の設定
		http.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/signIn")
				.usernameParameter("username")
				.passwordParameter("authority")
				.defaultSuccessUrl("/adminisratorTimeLine", true)
				.failureUrl("/login?error").permitAll();
		//ログアウトの設定
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll()
				.deleteCookies("JSESSIONID");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*ログイン時の挙動の設定
		 * jdbcAuthentication()でDBからユーザー情報を読み込むみたいです
		 */
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(UserSQL)
				.passwordEncoder(passwordEncoder());

	}

}
