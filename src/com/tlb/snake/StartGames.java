package com.tlb.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //绘制一个静态窗口
        JFrame jFrame=new JFrame("贪吃蛇");
        //设置界面的大小
        jFrame.setBounds(10,10,900,720);
        jFrame.setResizable(false);//窗口大小不可改变
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭时间，游戏可以关闭
        jFrame.setVisible(true);//让窗口能够展现出来

        //面板 JPanel 可以加入到JFrame上
        jFrame.add(new GameJpanel());



    }
}
