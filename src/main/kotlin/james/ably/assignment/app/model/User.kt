package james.ably.assignment.app.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    var email: String = "",
    @Column
    var nickName: String = "",
    @Column
    var encPassword: String = "",
    @Column
    var name: String = "",
    @Column
    var mobile: String = "",
    @Column @CreatedDate
    var createdAt: LocalDateTime? = null,
    @Column @LastModifiedDate
    var modifiedAt: LocalDateTime? = null
)