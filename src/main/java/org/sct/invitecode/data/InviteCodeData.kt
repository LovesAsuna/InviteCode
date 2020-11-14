package org.sct.invitecode.data

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.sct.invitecode.gui.Gui
import org.sct.invitecode.storge.Storge
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap
import kotlin.collections.HashSet

object InviteCodeData {
    /*计时器*/
    @JvmStatic
    var Timer: MutableMap<String, Long> = HashMap()

    /*注册*/
    @JvmStatic
    var register: MutableSet<UUID> = HashSet()

    /*储存离线玩家列表*/
    @JvmStatic
    var offlinelist: MutableList<String> = ArrayList()

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
    var gui: Gui = Gui()

    /*插件专用线程池*/
    @JvmStatic
    var pool: ThreadPoolExecutor = ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, ArrayBlockingQueue(5))

    fun getPlayerName(playerName: String?): String {
        val player = Bukkit.getPlayer(playerName!!)
        return player!!.name
    }


}