package org.sct.invitecode.storge;

import org.sct.invitecode.InviteCode;

import java.sql.*;

public class Mysql extends Storge {
    String sql;
    private Connection conn = null;

    public Mysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        InviteCode.getInstance().getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§aMysql数据库连接实例化中...");
        String database = InviteCode.getInstance().getConfig().getString("InviteCode.mySQLDatabase");
        String tablename = InviteCode.getInstance().getConfig().getString("InviteCode.mySQLTablename");
        String port = InviteCode.getInstance().getConfig().getString("InviteCode.mySQLPort");
        String user = InviteCode.getInstance().getConfig().getString("InviteCode.mySQLUsername");
        String password = InviteCode.getInstance().getConfig().getString("InviteCode.mySQLPassword");
        String ip = InviteCode.getInstance().getConfig().getString("InviteCode.mySQLHost");
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, tablename, new String[]{"Table"});
            boolean TableExist = rs.next();
            if (!TableExist) {
                sql = "create TABLE ic ("
                        + "player varchar (255),"
                        + "ic varchar (255)"
                        + ")";
                PreparedStatement pps = conn.prepareStatement(sql);
                pps.executeUpdate();
                pps.close();
                InviteCode.getInstance().getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§a数据库初始化成功!");
            } else {
                InviteCode.getInstance().getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§a数据库连接成功!");
            }
        } catch (SQLException e) {
            InviteCode.getInstance().getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§4Mysql数据库连接失败!");
        }
    }

    @Override
    public void storge(String player, String ic) {
        sql = "insert into ic (player,ic) values (?,?)";
        try {
            PreparedStatement pps = conn.prepareStatement(sql);
            pps.setString(1, player);
            pps.setString(2, ic);
            pps.executeUpdate();
            pps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(String player) {
        String ic = null;
        sql = "select player,ic from ic where player = " + "\"" + player + "\"";
        String invitecode = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ic = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ic;
    }

    @Override
    public String readplayer(String ic) {
        String playername = null;
        sql = "select player,ic from ic where ic = " + "\"" + ic + "\"";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                playername = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playername;
    }
}
