package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * プレイヤーの名前だけをファイルに保存・読み込みするシンプルなクラスです。
 * SaveManagerクラスとは異なり、名前のみを扱うため、より簡単なファイル操作を学ぶことができます。
 * 
 * 使い方の例：
 * // 名前を保存
 * NameSaver.saveName("勇者アレン", "player_name.txt");
 * 
 * // 名前を読み込み
 * String name = NameSaver.loadName("player_name.txt");
 * 
 * 保存先：C:\\work\\java-quest\\ディレクトリ
 * 
 */
public class NameSaver {
		
    /**
     * プレイヤーの名前をテキストファイルに保存します。
     * ファイルには名前だけが1行で書き込まれます。
     * 
     * 保存されるファイルの中身の例：
     * 勇者アレン
     * 
     * @param name 保存したいプレイヤーの名前（例："勇者アレン"）
     * @param filename 保存先のファイル名（例："player_name.txt"）
     */
    public static void saveName(String name, String filename) {
    	
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(name);  // 名前だけをファイルに書き込み
            System.out.println("名前を保存: " + name + " -> " +filename);
        } catch (IOException e) {
            System.out.println("保存失敗: " + e.getMessage());
        }
    }
    
    /**
     * テキストファイルからプレイヤーの名前を読み込みます。
     * ファイルの1行目に書かれた名前を取得します。
     * 
     * ファイルが存在しない場合や読み込みに失敗した場合は null を返します。
     * 
     * @param filename 読み込むファイル名（例："player_name.txt"）
     * @return 読み込んだプレイヤーの名前。失敗した場合は null
     */
    public static String loadName(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String name = reader.readLine();  // ファイルの1行目を読み込み
            System.out.println("名前を読み込み: " + name + " <- " + filename);
            return name;
        } catch (IOException e) {
            System.out.println("読み込み失敗: " + e.getMessage());
            return null;  // エラーの場合はnullを返す
        }
    }
    
    /**
     * NameSaverクラスの動作テストを行います。
     * 名前の保存と読み込みの一連の流れを確認できます。
     * 
     * 実行される処理：
     * 1. "勇者アレン"という名前を"player_name.txt"に保存
     * 2. 保存したファイルから名前を読み込み
     * 3. 読み込んだ名前を画面に表示
     * 
     * @param args コマンドライン引数（このプログラムでは使用しません）
     */
    public static void main(String[] args) {
        System.out.println("========== 名前保存テスト ==========\n");
        
        // 1. 名前をファイルに保存
        String file = "data/player_name.txt";
        System.out.println("--- 名前保存 ---");
        saveName("勇者アレン", file);
        
        // 2. 保存した名前を読み込み
        System.out.println("\n--- 名前読み込み ---");
        String name = loadName(file);
        
        // 3. 結果を表示
        if (name != null) {
            System.out.println("\n--- 結果 ---");
            System.out.println("プレイヤー名: " + name);
            System.out.println("テスト成功！名前の保存と読み込みが正常に動作しました。");
        } else {
            System.out.println("\nテスト失敗：名前の読み込みに失敗しました。");
        }
    }
}
