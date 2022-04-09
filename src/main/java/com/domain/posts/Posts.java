package com.domain.posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.domain.BaseTimeEtity;


@Getter//getter 자동생성
@NoArgsConstructor//기본생정자 자동 추가
@Entity//테이블과 링크
public class Posts extends BaseTimeEtity {
    @Id//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//옵션 추가 시 자동 증가
    private long id;

    @Column(length=500, nullable=false)//컬럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder//빌더 패턴, 생성자에 선언시 생성자에 포함된 필드만 빌더 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
