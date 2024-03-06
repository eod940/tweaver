package com.valuewith.tweaver.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
  @NotBlank(message = "후기를 작성할 그룹을 확인해주세요.")
  private Long tripGroupId;
  @NotBlank(message = "제목에 필요한 최소 글자 수를 맞춰주세요.")
  @Size(min = 2, message = "제목에 필요한 최소 글자 수를 맞춰주세요.")
  private String title;
  @NotBlank(message = "내용에 필요한 최소 글자 수를 맞춰주세요.")
  @Size(min = 2, message = "내용에 필요한 최소 글자 수를 맞춰주세요.")
  private String content;
}
