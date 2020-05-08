package org.sct.invitecode.data

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash
import org.sct.invitecode.gui.gui
import org.sct.invitecode.storge.Storge
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

object InviteCodeData {
    /*计时器*/
    @JvmStatic
    var Timer: MutableMap<String, Long>? = null

    /*注册*/
    @JvmStatic
    var register: MutableMap<String, Boolean>? = null

    /*储存离线玩家列表*/
    @JvmStatic
    var offlinelist: MutableList<String>? = null

    /*依赖启动状态*/
    @JvmStatic
    var useVault = false

    @JvmStatic
    var useAuthme = false

    /*经济依赖*/
    @JvmStatic
    var econ: Economy? = null

    /*数据库*/
    @JvmStatic
    var storge: Storge? = null

    /*gui*/
    @JvmStatic
    var gui: gui? = null

    /*插件专用线程池*/
    @JvmStatic
    var pool: ThreadPoolExecutor? = null

    fun getplayername(playername: String?): String {
        val player = Bukkit.getPlayer(playername!!)
        return player!!.name
    }

    init {
        Timer = HashMap()
        register = HashMap()
        offlinelist = ArrayList()
        gui = gui()
        pool = ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, ArrayBlockingQueue(5))
    }


}