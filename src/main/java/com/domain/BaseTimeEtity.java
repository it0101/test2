package com.domain;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //createdDate 도 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEtity {

    @CreatedDate //entity 생성 시 시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate//entity 값 변경 시 시간 자동저장
    private LocalDateTime modifiedDate;

}
