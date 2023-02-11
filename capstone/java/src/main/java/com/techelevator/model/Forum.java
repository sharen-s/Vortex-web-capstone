package com.techelevator.model;

import java.util.List;

public class Forum {

    private int id;
    private String forumName;
    private String description;
    private String rules;
    private List<Integer> moderatorList;
    //private LocalDateTime create;

    public Forum() {
    }

    public Forum(int id, String forumName, String description, String rules) {
        this.id = id;
        this.forumName = forumName;
        this.description = description;
        this.rules = rules;

    }

    public List<Integer> getModeratorList() {
        return moderatorList;
    }

    public void setModeratorList(List<Integer> moderatorList) {
        this.moderatorList = moderatorList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

//    public LocalDateTime getCreate() {
//        return create;
//    }
//
//    public void setCreate(LocalDateTime create) {
//        this.create = create;
//    }


}
