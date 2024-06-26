package com.valuewith.tweaver.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valuewith.tweaver.auditing.BaseEntity;
import com.valuewith.tweaver.chat.entity.ChatRoom;
import com.valuewith.tweaver.member.entity.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "MESSAGE")
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE message SET IS_DELETED = 1 WHERE MESSAGE_ID = ?")
@Where(clause = "IS_DELETED = 0")
public class Message extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long messageId;

  @NotNull
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chat_room_id")
  private ChatRoom chatRoom;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  public static Message from(ChatRoom chatRoom, Member member, String content) {
    return Message.builder()
        .content(content)
        .chatRoom(chatRoom)
        .member(member)
        .build();
  }
}
