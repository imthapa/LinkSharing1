package linksharing

class ReadingItemController {

    def index() { }

    def changeIsRead(Long id, Boolean isRead){
        log.info("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj$id     pppp n      $isRead")
        render "${ReadingItem.changeIsRead(id,isRead,session.user.id)}"

    }
}
