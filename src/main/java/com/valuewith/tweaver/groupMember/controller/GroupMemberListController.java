package com.valuewith.tweaver.groupMember.controller;

import com.valuewith.tweaver.commons.security.TokenService;
import com.valuewith.tweaver.constants.ApprovedStatus;
import com.valuewith.tweaver.groupMember.dto.GroupMemberListDto;
import com.valuewith.tweaver.groupMember.service.GroupMemberListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups/*")
public class GroupMemberListController {

    private final GroupMemberListService groupMemberListService;
    private final TokenService tokenService;

    @GetMapping("members/{tripGroupId}")
    public ResponseEntity<List<GroupMemberListDto>> getGroupMemberFilteredStatusList(
            @RequestHeader("Authorization") String token,
            @PathVariable Long tripGroupId,
            @RequestParam String status
    ) {
        String memberEmail = tokenService.getMemberEmail(token);
        log.info("😀" + memberEmail);
        List<GroupMemberListDto> groupMemberListDtoList
                = groupMemberListService.getFilteredGroupMembers(memberEmail, tripGroupId, status);
        return ResponseEntity.ok(groupMemberListDtoList);
    }

}