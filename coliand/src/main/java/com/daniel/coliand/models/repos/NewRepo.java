package com.daniel.coliand.models.repos;

public class NewRepo {
    public String name;
    public boolean autoInit;
    public boolean Private;
    public String gitignore_template;

    public NewRepo() {
    }

    public NewRepo(String name, boolean autoInit, boolean aPrivate, String gitignore_template) {
        this.name = name;
        this.autoInit = autoInit;
        Private = aPrivate;
        this.gitignore_template = gitignore_template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutoInit() {
        return autoInit;
    }

    public void setAutoInit(boolean autoInit) {
        this.autoInit = autoInit;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        Private = aPrivate;
    }

    public String getGitignore_template() {
        return gitignore_template;
    }

    public void setGitignore_template(String gitignore_template) {
        this.gitignore_template = gitignore_template;
    }
}
