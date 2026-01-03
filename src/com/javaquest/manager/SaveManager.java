package com.javaquest.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.javaquest.model.Player;

/**
 * ゲームのセーブ・ロード機能を管理するクラスです。
 * プレイヤーのデータをファイルに保存したり、ファイルから読み込んだりできます。
 * 
 * 使い方の例：
 * // セーブする場合
 * Player player = new Player("勇者", 100, 5);
 * SaveManager.save(player, "save_data.txt");
 * 
 * // ロードする場合
 * Player loadedPlayer = SaveManager.load("save_data.txt");
 * 
 */
public class SaveManager {
    /**
     * プレイヤーのデータをテキストファイルに保存します。
     * 名前、現在のHP、最大HP、レベルの順番で1行ずつ保存されます。
     * 
     * 保存されるファイルの中身の例：
     * 勇者
     * 80
     * 100
     * 5
     * 
     * @param player 保存したいプレイヤーオブジェクト
     * @param filename 保存先のファイル名（例："save_data.txt"）
     */
    public static void save(Player player, String filename) {
    	
        try (FileWriter writer = new FileWriter(filename)) {
            // プレイヤーのデータを1行ずつファイルに書き込み
            writer.write(player.getName() + "\n");      // 名前
            writer.write(player.getHp() + "\n");          // 現在のHP
            writer.write(player.getMaxHp() + "\n");     // 最大HP
            writer.write(player.getLevel() + "\n");       // レベル
            
            // 装備が設定されていれば、追加
            String weapon = player.getWeapon();
            if(player.getWeapon() != null) {
            	weapon = player.getWeapon();
            }
            writer.write(weapon + "\n");
            
            // 防具が設定されていれば、追加
            String armor = "";
            if(player.getArmor() != null) {
            	armor = player.getArmor();
            }
            writer.write(armor + "\n");
            
            System.out.println("セーブ完了: " + filename);
            
        } catch (IOException e) {
            System.out.println("セーブ失敗: " + e.getMessage());
        }
    }
    /**
     * テキストファイルからプレイヤーのデータを読み込んで、Playerオブジェクトを作成します。
     * ファイルは save メソッドで保存した形式である必要があります。
     * 
     * ファイルが見つからない場合や、データが正しくない場合は null を返します。
     * その場合はエラーメッセージが画面に表示されます。
     * 
     * @param filename 読み込むファイル名（例："save_data.txt"）
     * @return 読み込んだプレイヤーオブジェクト。失敗した場合は null
     */
    public static Player load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // ファイルからデータを1行ずつ読み込み
            String name = reader.readLine();                         // 1行目：名前
            int hp = Integer.parseInt(reader.readLine());        // 2行目：現在のHP
            int maxHp = Integer.parseInt(reader.readLine());  // 3行目：最大HP
            int level = Integer.parseInt(reader.readLine());      // 4行目：レベル
            String weapon = reader.readLine();  // 追加
            String armor = reader.readLine();     // 追加

            // 読み込んだデータでPlayerオブジェクトを作成
            Player player = new Player(name, maxHp, level);
            player.setHp(hp);
            player.setWeapon(weapon);  // 武器を装備
            player.setArmor(armor);       // 防具を装備
           
            System.out.println("ロード完了: " + filename);
            return player;
            
        } catch (IOException e) {
            System.out.println("ロード失敗: " + e.getMessage());
            return null;  // エラーの場合はnullを返す
        }
    }
}