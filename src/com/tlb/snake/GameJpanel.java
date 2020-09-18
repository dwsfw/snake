package com.tlb.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJpanel extends JPanel implements KeyListener, ActionListener {
    int length;//蛇的坐标
    int[] snakeX=new int[600];//x坐标
    int[] snakeY=new int[500];//y坐标
    String fx;//蛇的方向,默认向右
    boolean isStart=false;//游戏是否开始
    Timer timer=new Timer(100,this);//定时器
    //定义一个食物
    int foodX;
    int foodY;
    Random random=new Random();
    //死亡判断
    boolean isFail;
    //积分系统
    int score;
    public void init(){
        length =3;
        snakeX[0]=100;snakeY[0]=100;//头部坐标
        snakeX[1]=75;snakeY[1]=100;//第一个身体坐标
        snakeX[2]=50;snakeY[2]=100;//第二个身体坐标
        fx="R";
        isFail=false;
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
        score=0;
    }

    public GameJpanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
    }
    //画板，画界面，画蛇
    //Graphics 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        System.out.println("gogogo");
        this.setBackground(Color.WHITE);//设置背景颜色
        //绘制头部
        Data.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,600);
        //画一条静态的小蛇
        if(fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if(fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);//蛇的身体长度通过length
        }
//        画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度："+length,750,30);
        g.drawString("成绩："+score,750,50);
        //画食物
        Data.food.paintIcon(this,g,foodX,foodY);
        //游戏是否开始
        if(!isStart)
        {
            g.setColor(Color.WHITE);//画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }
        //失败提示
        if (isFail) {
            g.setColor(Color.WHITE);//画笔的颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败，空格重新开始游戏",300,300);
        }
    }
    //监听键盘的输入
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        int keyCode =e.getKeyCode();
        System.out.println("down");
        if(keyCode ==KeyEvent.VK_SPACE);//按下的是空格
        {
            if(isFail){
                //失败游戏再来一遍
                init();//初始化游戏
            }
            else
                isStart=!isStart;
            repaint();//刷新界面
        }
        //键盘控制方向
        if(keyCode ==KeyEvent.VK_LEFT)
            fx="L";
        else if(keyCode ==KeyEvent.VK_RIGHT)
            fx="R";
        else if(keyCode==KeyEvent.VK_UP)
            fx="U";
        else if(keyCode==KeyEvent.VK_DOWN)
            fx="D";
    }
    //定时器，监听时间，帧，执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有失败
        if(isStart&&!isFail){
            System.out.println("start");
            //右移
            for (int i = length-1; i >0 ; i--) {//除了脑袋，身体向前移
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }
            //通过控制方向让头部移动
            if(fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;//头部移动
                if (snakeX[0] > 850)
                    snakeX[0] = 25;
            }else if(fx.equals("L"))
            {
                snakeX[0] = snakeX[0] - 25;//头部移动
                if (snakeX[0] <25)
                    snakeX[0] = 850;
            }else if(fx.equals("U"))
            {
                snakeY[0] = snakeY[0] - 25;//头部移动
                if (snakeY[0] <75)
                    snakeY[0] = 650;
            }else if(fx.equals("D")){
                snakeY[0] = snakeY[0] + 25;//头部移动
                if (snakeY[0] >650)
                    snakeY[0] = 75;
            }
            //蛇吃食物
            if(snakeX[0]==foodX&&snakeY[0]==foodY){
                length++;
                score+=10;
                //重新生成食物
                foodX=25+25*random.nextInt(34);
                foodY=75+25*random.nextInt(24);
            }
//            结束判断
            for (int i = 1; i < length; i++) {
                if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i])
                    isFail=true;
            }
            repaint();
        }
        timer.start();//让时间动起来
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下弹起

    }
    @Override
    public void keyReleased(KeyEvent e) {
        //键盘释放
    }


}
