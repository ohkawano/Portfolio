package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//投稿部分のEntitiy
@Table(name = "talk")
@Entity
public class Talk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDateTime nowdate;

	@NotBlank
	@Size(max = 10)
	private String name;

	@NotBlank
	@Size(max = 500)
	private String comment;

	public Talk() {

	}

	public Talk(long id, @NotBlank @Size(max = 10) String name, LocalDateTime nowdate,
			@NotBlank @Size(max = 500) String comment) {
		super();
		this.id = id;
		this.nowdate = nowdate;
		this.name = name;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getNowdate() {
		return nowdate;
	}

	public void setNowdate(LocalDateTime nowdate) {
		this.nowdate = nowdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
