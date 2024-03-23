package jp.co.kiramex.dbSample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Review05 {

    public static void main(String[] args) {
        // データベース接続情報
        String url = "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "Nappi730agape";

        // データベース接続用変数
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // データベースに接続
            con = DriverManager.getConnection(url, user, password);

            try (// キーボードからIDを入力
            Scanner scanner = new Scanner(System.in)) {
                System.out.print("検索キーワードを入力してください > ");
                int id = Integer.parseInt(scanner.nextLine());

                String sql = "SELECT * FROM person WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id);
            } catch (NumberFormatException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            } finally {
            }

            rs = pstmt.executeQuery();

            // 結果出力
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(name);
                System.out.println(age);
            } else {
                System.out.println("該当するデータがありません");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 接続をクローズ
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
