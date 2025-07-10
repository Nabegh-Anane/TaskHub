package com.taskhub.taskhub_backend.services;

import com.taskhub.taskhub_backend.models.Group;
import com.taskhub.taskhub_backend.models.User;
import com.taskhub.taskhub_backend.repositories.GroupRepository;
import com.taskhub.taskhub_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroup(Long id) {
        return groupRepository.findById(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group updateGroup(Long id, Group updatedGroup) {
        return groupRepository.findById(id).map(group -> {
            group.setName(updatedGroup.getName());
            return groupRepository.save(group);
        }).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group addUserToGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (!group.getUsers().contains(user)) {
            group.getUsers().add(user);
            groupRepository.save(group);
        }
        return group;
    }

    public Group removeUserFromGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (group.getUsers().contains(user)) {
            group.getUsers().remove(user);
            groupRepository.save(group);
        }
        return group;
    }
}
