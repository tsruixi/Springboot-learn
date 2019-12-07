package com.example.community.learn.dto;

/**
 * @Author: ruixi
 * @Date: 2019/12/7 16:00
 */
public class GithubUser {
        private String name;
        private Long id;
        private String bio;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getBio() {
                return bio;
        }

        public void setBio(String bio) {
                this.bio = bio;
        }
}
