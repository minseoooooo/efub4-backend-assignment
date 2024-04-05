package efub.assignment.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity { // 이 엔티티를 이용해서 나머지를 상속받아서 사용(?) 암튼 그렇다함..
    @CreatedDate
    @Column(updatable = false) // 데이터베이스에 컬럼으로
    private LocalDateTime createdDate; // createdDate 자동

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedDate; // modifiedDate 자동


}
