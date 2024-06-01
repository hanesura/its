package com.example.its.domain.issue;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<IssueEntity> findAll() {
        return issueRepository.findAll();
    }

    @Transactional
    public void create(@Param("summary") String summary, @Param("description")String description) {
        issueRepository.insert(summary, description);
    }

    public IssueEntity findById(long issueId) {
        return issueRepository.findById(issueId);
    }
}
