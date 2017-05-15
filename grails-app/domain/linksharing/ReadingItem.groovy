package linksharing

import com.ttnd.linksharing.co.SearchCO

class ReadingItem {

    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        user(nullable: false)
        resource(nullable: false, unique: 'user')
        isRead(nullable: false)
    }

    //todo Q6c)

    @Override
    public String toString() {
        return "ReadingItem{" +
                "id=" + id +
                ", resource=" + resource +
                ", user=" + user +
                ", isRead=" + isRead +
                '}';
    }

    //todo GORM2 Q6
    /*
       Create readingItem/changeIsRead action which takes Long id and Boolean isRead
        - User executeUpdate to change the isRead of readingItem with given id
        - If value returned by executeUpdate is 0 then render error else render success
    */
    //working fine
//    static def changeIsRead(ReadingItem readingItem) {
//
//
//    }

}
