package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * プレイヤーのステータス（HP、MP、レベル）をCSV形式で保存・読み込みするクラスです。
 * データをカンマ区切りで保存するため、シンプルで読みやすいファイル形式を学ぶことができます。
 * 
 * 保存形式：CSV（Comma Separated Values）
 * ファイルの中身の例：100,50,5（HP,MP,レベルの順）
 * 
 * 使い方の例：
 * // ステータスを保存
 * StatusSaver.save(100, 50, 5, "status.txt");
 * 
 * // ステータスを読み込み
 * int[] status = StatusSaver.load("status.txt");
 * int hp = status[0], mp = status[1], level = status[2];
 * 
 */
public class StatusSaver {
    /**
     * プレイヤーのステータスをCSV形式でファイルに保存します。
     * HP、MP、レベルをカンマで区切って1行で保存します。
     * 
     * 保存されるファイルの中身の例：
     * 100,50,5
     * （HP:100、MP:50、レベル:5の意味）
     * 
     * @param hp プレイヤーのHP（体力）
     * @param mp プレイヤーのMP（魔法力）
     * @param level プレイヤーのレベル
     * @param filename 保存先のファイル名（例："status.txt"）
     */
    public static void save(int hp, int mp, int level, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // HP、MP、レベルをカンマ区切りで保存（CSV形式）
            writer.write(hp + "," + mp + "," + level);
            System.out.println("ステータス保存完了: HP=" + hp + ", MP=" + mp + ", Level=" + level);
        } catch (IOException e) {
            System.out.println("保存失敗: " + e.getMessage());
        }
    }
    
    /**
     * CSV形式で保存されたステータスデータをファイルから読み込みます。
     * カンマで区切られたデータを分割して、整数の配列として返します。
     * 
     * 返される配列の構造：
     * - status[0]: HP（体力）
     * - status[1]: MP（魔法力）
     * - status[2]: Level（レベル）
     * 
     * ファイルが存在しない場合やデータが正しくない場合は null を返します。
     * 
     * @param filename 読み込むファイル名（例："status.txt"）
     * @return ステータスデータの配列[HP, MP, Level]。失敗した場合は null
     */
    public static int[] load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();  // ファイルの1行目を読み込み
            String[] parts = line.split(",");     // カンマで文字列を分割
            
            // 分割した文字列を整数に変換
            int hp = Integer.parseInt(parts[0]);     // 1番目：HP
            int mp = Integer.parseInt(parts[1]);     // 2番目：MP
            int level = Integer.parseInt(parts[2]);   // 3番目：レベル
            
            System.out.println("ステータス読み込み完了: HP=" + hp + ", MP=" + mp + ", Level=" + level);
            return new int[]{hp, mp, level};  // 配列として返す
            
        } catch (IOException e) {
            System.out.println("読み込み失敗: " + e.getMessage());
            return null;  // エラーの場合はnullを返す
            
        } catch (NumberFormatException e) {
            System.out.println("データ形式エラー: 数値ではないデータが含まれています");
            return null;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("データ不足エラー: HP、MP、レベルの3つのデータが必要です");
            return null;
        }
    }
    
    /**
     * StatusSaverクラスの動作テストを行います。
     * CSV形式でのステータス保存と読み込みの一連の流れを確認できます。
     * 
     * 実行される処理：
     * 1. サンプルステータス（HP:100, MP:50, Level:5）を"status.txt"に保存
     * 2. 保存したファイルからステータスを読み込み
     * 3. 読み込んだデータを個別に表示して確認
     * 
     * @param args コマンドライン引数（このプログラムでは使用しません）
     */
    public static void main(String[] args) {
    	System.out.println("========== ステータス保存テスト ==========\n");
    	
    	// 保存先ファイルのパスを設定
    	String file = "data/status.txt";
    	
        // 1. サンプルステータスをCSV形式で保存
        System.out.println("--- ステータス保存 ---");
        save(100, 50, 5, file);  // HP:100, MP:50, Level:5を保存
        
        // 2. 保存したステータスを読み込み
        System.out.println("\n--- ステータス読み込み ---");
        int[] status = load(file);
        
        // 3. 読み込んだデータを表示して確認
        if (status != null) {
            System.out.println("\n--- 結果 ---");
            System.out.println("HP: " + status[0] + " (体力)");
            System.out.println("MP: " + status[1] + " (魔法力)");
            System.out.println("Level: " + status[2] + " (レベル)");
            System.out.println("\nテスト成功！CSV形式でのステータス保存と読み込みが正常に動作しました。");
        } else {
            System.out.println("\nテスト失敗：ステータスの読み込みに失敗しました。");
        }
    }
}