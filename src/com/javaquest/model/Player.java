package com.javaquest.model;

/**
 * ゲームのプレイヤーを表すクラスです。
 * プレイヤーの名前、HP（体力）、レベル、装備情報（武器、防具）などの情報を管理します。
 * 
 * 使い方の例：
 * Player player = new Player("勇者", 100, 1);
 * player.setWeapon("鉄の剣");  // 武器を装備
 * player.setArmor("革の防具");   // 防具を装備
 * player.displayStatus(); // プレイヤーの情報を表示
 * 
 * @author 高校生プログラマー
 * @version 2.0 - 装備情報機能を追加
 */
public class Player {
    /** プレイヤーの名前（例："勇者"、"魔法使い"など） */
    private String name;
    
    /** 現在のHP（体力）。0になると倒れてしまいます */
    private int hp;
    
    /** 最大HP（体力の上限）。回復するときの目安になります */
    private int maxHp;
    
    /** プレイヤーのレベル。強さを表します */
    private int level;
    
    /** 装備中の武器名（例："鉄の剣"、"魔法の杖"など）。攻撃力に影響します */
    private String weapon;
    
    /** 装備中の防具名（例："革の防具"、"鉄の鎧"など）。防御力に影響します */
    private String armor;

    /**
     * 新しいプレイヤーを作成します。
     * 作成時は体力が満タンの状態でスタートします。
     * 装備は初期状態では何も装備していない状態（null）です。
     * 
     * @param name プレイヤーの名前（例："勇者"）
     * @param maxHp 最大HP（体力の上限、例：100）
     * @param level プレイヤーのレベル（例：1）
     */
    public Player(String name, int maxHp, int level) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;  // 最初は体力満タン
        this.level = level;
        this.weapon = "";  // nullで初期化(未装備)
        this.armor = "" ;    // nullで初期化(未装備)
    }
    
    /**
     * プレイヤーの名前を取得します。
     * @return プレイヤーの名前
     */
    public String getName() { return name; }
    
    /**
     * 現在のHPを取得します。
     * @return 現在のHP（0以上の数値）
     */
    public int getHp() { return hp; }
    
    /**
     * 最大HPを取得します。
     * @return 最大HP（体力の上限値）
     */
    public int getMaxHp() { return maxHp; }
    
    /**
     * プレイヤーのレベルを取得します。
     * @return 現在のレベル
     */
    public int getLevel() { return level; }
    
    /**
     * 装備中の武器名を取得します。
     * @return 装備中の武器名。武器を装備していない場合は null
     */
    public String getWeapon() { return weapon; }
    
    /**
     * 装備中の防具名を取得します。
     * @return 装備中の防具名。防具を装備していない場合は null
     */
    public String getArmor() { return armor; }
    
    /**
     * プレイヤーのHPを設定します。
     * ダメージを受けたり、回復したりするときに使います。
     * 
     * 注意：HPは0未満にならないように、また最大HPを超えないように
     * 呼び出し側で調整してください。
     * 
     * @param hp 設定したいHP（0以上、maxHp以下が推奨）
     */
    public void setHp(int hp) { this.hp = hp; }
    
    /**
     * プレイヤーの武器を装備します。
     * 新しい武器を装備すると、以前の武器は自動的に外れます。
     * 
     * @param weapon 装備したい武器名（例："鉄の剣"）。nullを指定すると武器を外します
     */
    public void setWeapon(String weapon) { 
    	if(weapon != null) 	this.weapon = weapon; 
    }
    
    /**
     * プレイヤーの防具を装備します。
     * 新しい防具を装備すると、以前の防具は自動的に外れます。
     * 
     * @param armor 装備したい防具名（例："革の防具"）。nullを指定すると防具を外します
     */
    public void setArmor(String armor) { 
    	if(armor != null) this.armor = armor; 
    }


    /**
     * プレイヤーの現在の状態を画面に表示します。
     * 名前、HP、レベル、装備情報を見やすい形で出力します。
     * 
     * 表示例：
     * ========== ステータス ==========
     * 名前: 勇者
     * HP: 80/100
     * レベル: 5
     * 武器: 鉄の剣
     * 防具: 革の防具
     * ===============================
     */
    public void displayStatus() {
        System.out.println("========== ステータス ==========");
        System.out.println("名前: " + name);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("レベル: " + level);
        
        // 装備情報を表示（装備していない場合は"なし"と表示）
        System.out.println("武器: " + (weapon.isEmpty() ? "なし" : weapon));
        System.out.println("防具: " + (armor.isEmpty() ?  "なし"  : armor));
        
        System.out.println("===============================");
    }
    
}