package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Talk;

//コメント投稿のためのRepository
public interface TalkRepository extends JpaRepository<Talk, Long> {

}
