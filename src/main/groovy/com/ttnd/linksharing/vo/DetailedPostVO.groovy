package com.ttnd.linksharing.vo

/**
 * Created by ishwar on 13/5/17.
 */
class DetailedPostVO {

    long createdById
    String userName
    String fullName
    String topicName
    long topicId
    String description
    int ratings
    Date updated
    long resourceID

    @Override
    public String toString() {
        return "DetailedPostVO{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", updated=" + updated +
                ", resourceID=" + resourceID +
                '}';
    }
}
