package linksharing

class ReadingItemController {

    def index() {}

    def changeIsRead(Long id, Boolean isRead) {
        ReadingItem readingItem = ReadingItem.findByUserAndResource(session.user, Resource.get(id))
        log.info("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj$id     $session.user.id +  $isRead")
        if(readingItem){
            int flag = ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id",
                    [isRead: !readingItem.isRead,id:readingItem.id ])
            if (flag == 0){
                flash.error = "error"
            }
            else{
                flash.message = "successfully updated"
            }
        }else{
            flash.error = "reading item not found"
        }
        redirect(controller: "user", action: "index")


    }
}
