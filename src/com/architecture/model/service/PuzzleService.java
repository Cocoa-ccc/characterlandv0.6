package com.architecture.model.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PuzzleService {
    // 原有填空题库：题目 → {答案, 解析}
    private Map<String, String[]> fillPuzzleBank = new HashMap<>();
    // 新增选择题库：题目 → {选项数组, 正确选项字母, 解析}
    private Map<String, Object[]> choicePuzzleBank = new HashMap<>();

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public PuzzleService() {
        // 初始化填空题库
        initFillPuzzleBank();
        // 初始化选择题库
        initChoicePuzzleBank();
    }

    // 初始化填空题库
    private void initFillPuzzleBank() {
        fillPuzzleBank.put("80 - 15 * 3 = ?",
                new String[]{"35", "解析：根据数学运算优先级，先算乘法15×3=45，再算减法80-45=35"});
        fillPuzzleBank.put("什么东西越洗越脏？",
                new String[]{"水", "解析：水用来洗东西时，会沾染上污渍，所以越洗越脏"});
        fillPuzzleBank.put("Java中private修饰的成员只能在哪访问？",
                new String[]{"本类", "解析：private是私有权限，仅能在当前类内部访问，子类/同包都无法直接访问"});

    }

    // 初始化选择题库
    private void initChoicePuzzleBank() {
        choicePuzzleBank.put("以下哪个是Java的基本数据类型？",
                new Object[]{
                        new String[]{"A. String", "B. int", "C. List", "D. Map"},
                        "B",
                        "解析：Java基本数据类型包括8种（byte/short/int/long/float/double/char/boolean），String/List/Map都是引用类型"
                });
        choicePuzzleBank.put("为什么美国白人都不喜欢靠近墨西哥？",
                new Object[]{
                        new String[]{"A.墨西哥很乱 ", "B.种族歧视 ", "C.近墨者黑 ", "D.不知道 "},
                        "C",
                        "解析：因为近“墨”者黑。"
                });
        choicePuzzleBank.put("最幸福的工具是什么？",
                new Object[]{
                        new String[]{"A.游标卡尺 ", "B.锤子 ", "C.扳手 ", "D.剪刀 "},
                        "A",
                        "解析：因为游标卡尺不估读。"
                });
        choicePuzzleBank.put("磁铁到了哪里会变成同性相吸？",
                new Object[]{
                        new String[]{"A.成都 ", "B.南极 ", "C.北极 ", "D.不知道 "},
                        "A",
                        "解析：你懂得，同性相吸。"
                });
        choicePuzzleBank.put("非洲的龙卷风除了叫扫黑风暴还叫什么？",
                new Object[]{
                        new String[]{"A.黑旋风 ", "B.士力架 ", "C.龙卷风 ", "D.以上都对 "},
                        "B",
                        "解析：因为士力架横扫饥饿。"
                });
        choicePuzzleBank.put("把名人做成游戏角色，谁最厉害？",
                new Object[]{
                        new String[]{"A.特朗普 ", "B.车力巨人 ", "C.霍金 ", "D.普京 "},
                        "C",
                        "解析：因为他是轮椅英雄。"
                });
        choicePuzzleBank.put("路易十六断头后变成什么了？",
                new Object[]{
                        new String[]{"A.路易十八 ", "B.诗人 ", "C.断头皇帝 ", "D.不知道 "},
                        "A",
                        "解析：看字形。"
                });
        choicePuzzleBank.put("电锯惊魂云海学长和予白学长被关在密室里，互相锯下对方二两肉才能放他们出去，多一克少一克都不行，请问为什么？",
                new Object[]{
                        new String[]{"A.不这么做就出不去 ", "B.剧情安排 ", "C..因为哥们儿相锯必须整二两 ", "D.不知道 "},
                        "C",
                        "解析：因为哥们儿相锯（相聚）必须整二两。"
                });
        choicePuzzleBank.put("东东哥被高压电电了会怎么样？",
                new Object[]{
                        new String[]{"A.会变成高雅人士 ", "B.会变黑 ", "C.会进医院 ", "D.会出事 "},
                        "A",
                        "解析：会变成高雅（高压）人士"
                });
        choicePuzzleBank.put("一个女大是十八变，两个东东哥差不多是什么？",
                new Object[]{
                        new String[]{"A.唐僧 ", "B.孙悟空 ", "C.八戒 ", "D.白龙马 "},
                        "B",
                        "解析：因为悟空72变。"
                });
        choicePuzzleBank.put("谁烤的肉最嫩？",
                new Object[]{
                        new String[]{"A.厨师 ", "B.吃货 ", "C.老师 ", "D.云海学长 "},
                        "C",
                        "解析：因为烤（考）的都没有焦（教）。"
                });
        choicePuzzleBank.put("十年生死两茫茫，鸡年生死什么茫茫？",
                new Object[]{
                        new String[]{"A.半 ", "B.一 ", "C.2.5 ", "D.不知道 "},
                        "A",
                        "解析：10对应二，2.5是10的1/4，2就对应0.5。"
                });
        choicePuzzleBank.put("东东哥没有钱了怎么办？",
                new Object[]{
                        new String[]{"A.打工 ", "B.创业 ", "C.抠抠膝盖 ", "D.躺平 "},
                        "C",
                        "解析：因为男儿膝下有黄金。"
                });


    }

    // 随机获取题型（填空/选择）
    private boolean isChoiceType() {
        // 50%概率出选择题，50%出填空题，可调整random.nextInt(2)的数字
        return random.nextInt(10) < 7;
    }

    // 随机获取填空题
    private String getRandomFillPuzzle() {
        String[] questions = fillPuzzleBank.keySet().toArray(new String[0]);
        return questions[random.nextInt(questions.length)];
    }

    // 随机获取选择题
    private String getRandomChoicePuzzle() {
        String[] questions = choicePuzzleBank.keySet().toArray(new String[0]);
        return questions[random.nextInt(questions.length)];
    }

    // 验证填空题答案
    private boolean checkFillAnswer(String question, String answer, String[] result) {
        String[] puzzleData = fillPuzzleBank.get(question);
        String correctAnswer = puzzleData[0];
        result[0] = puzzleData[1]; // 把解析存入结果数组
        return correctAnswer != null && correctAnswer.trim().equalsIgnoreCase(answer.trim());
    }

    // 验证选择题答案
    private boolean checkChoiceAnswer(String question, String answer, String[] result) {
        Object[] puzzleData = choicePuzzleBank.get(question);
        String correctAnswer = (String) puzzleData[1];
        result[0] = (String) puzzleData[2]; // 把解析存入结果数组
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }

    // 核心：解密交互流程（兼容填空+选择，新增解析展示）
    public boolean startPuzzle() {
        // 用于存储解析的临时数组
        String[] analysis = new String[1];
        // 随机选择题型
        boolean isChoice = isChoiceType();
        // 记录最终的答题结果
        boolean isCorrect = false;

        if (isChoice) {
            // 选择题逻辑
            String question = getRandomChoicePuzzle();
            Object[] puzzleData = choicePuzzleBank.get(question);
            String[] options = (String[]) puzzleData[0];
            String correctAnswer = (String) puzzleData[1];

            System.out.println("\n=== 宝箱解密（选择题）===");
            System.out.println("题目：" + question);
            System.out.println("选项：");
            for (String option : options) {
                System.out.println("  " + option);
            }
            System.out.print("请输入答案（输入A/B/C/D）：");
            String userAnswer = scanner.nextLine().trim().toUpperCase();

            if (checkChoiceAnswer(question, userAnswer, analysis)) {
                System.out.println(" 答案正确！");
                isCorrect = true;
            } else {
                System.out.println(" 答案错误！正确答案是：" + correctAnswer);
                System.out.println(analysis[0]); // 展示解析
                isCorrect = false;
            }
        } else {
            // 填空题逻辑
            String question = getRandomFillPuzzle();
            System.out.println("\n=== 宝箱解密（填空题）===");
            System.out.println("题目：" + question);
            System.out.print("请输入答案：");
            String userAnswer = scanner.nextLine();

            if (checkFillAnswer(question, userAnswer, analysis)) {
                System.out.println(" 答案正确！");
                isCorrect = true;
            } else {
                String correctAnswer = fillPuzzleBank.get(question)[0];
                System.out.println(" 答案错误！正确答案是：" + correctAnswer);
                System.out.println(analysis[0]); // 展示解析
                isCorrect = false;
            }
        }

        // 新增：无论答对答错，都暂停等待用户输入回车
        System.out.println("\n【请按回车键继续...】");
        scanner.nextLine();

        // 最后统一返回结果
        return isCorrect;
    }

    public String getRandomPuzzle() {
        return isChoiceType() ? getRandomChoicePuzzle() : getRandomFillPuzzle();
    }

    public boolean checkAnswer(String question, String answer) {
        String[] analysis = new String[1];
        if (fillPuzzleBank.containsKey(question)) {
            return checkFillAnswer(question, answer, analysis);
        } else if (choicePuzzleBank.containsKey(question)) {
            return checkChoiceAnswer(question, answer, analysis);
        }
        return false;
    }

    // 测试主方法
    public static void main(String[] args) {
        PuzzleService puzzleService = new PuzzleService();
        puzzleService.startPuzzle();
    }
}