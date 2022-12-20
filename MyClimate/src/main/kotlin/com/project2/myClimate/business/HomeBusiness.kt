package com.project2.myClimate.business

import com.project2.myClimate.dao.HomeRepository
import com.project2.myClimate.model.Home
import com.project2.myClimate.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeBusiness: IHomeBusiness {

    @Autowired
    lateinit var homeRepository: HomeRepository
    override fun listInfo(idHome: Long?): Home {
        TODO("Not yet implemented")
    }

    override fun register(name: String, address: String, description: String, idOwner: Long): Home {
        TODO("Not yet implemented")
    }

    override fun delete(idHome: Long, owner: User) {
        TODO("Not yet implemented")
    }

    override fun modify(idHome: Long, name: String, address: String, description: String, owner: User): Home {
        TODO("Not yet implemented")
    }

    override fun searchHomeFull(owner: User?, address: String, description: String): Home {
        TODO("Not yet implemented")
    }


    override fun searchHomePartial(address: String, description: String): Home {
        TODO("Not yet implemented")
    }

}