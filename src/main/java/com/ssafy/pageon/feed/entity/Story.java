package com.ssafy.pageon.feed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kakao.rebit.book.entity.Book;
import kakao.rebit.common.domain.ImageKeyModifier;
import kakao.rebit.member.entity.Member;

@Entity
@DiscriminatorValue("S")
public class Story extends Feed implements ImageKeyModifier {
    
    private String imageKey;
    
    @Column(length = 1000)
    private String content;
    
    protected Story() {
    }
    
    public Story(Member member, Book book, String imageKey, String content) {
        super(member, book);
        this.imageKey = imageKey;
        this.content = content;
    }
    
    public void updateTextFields(String content) {
        this.content = content;
    }
    
    @Override
    public void changeImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
    
    public String getImageKey() {
        return imageKey;
    }
    
    public String getContent() {
        return content;
    }
}
}
