package com.valuewith.tweaver.groupMember.repository;

import static com.valuewith.tweaver.groupMember.entity.QGroupMember.groupMember;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.valuewith.tweaver.constants.ApprovedStatus;
import com.valuewith.tweaver.group.entity.QTripGroup;
import com.valuewith.tweaver.groupMember.entity.GroupMember;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupMemberRepositoryCustomImpl implements GroupMemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupMember> findApprovedMembersByTripGroupId(Long tripGroupId) {
        return queryFactory
            .selectFrom(groupMember)
            .join(groupMember.tripGroup, QTripGroup.tripGroup).fetchJoin()
            .where(groupMember.tripGroup.tripGroupId.eq(tripGroupId)
                .and(groupMember.approvedStatus.eq(ApprovedStatus.APPROVED)))
            .fetch();
    }
}
