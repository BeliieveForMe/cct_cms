package com.guodf.owner.controller;

/**
 * @description: 晋升考试伪代码
 *      1、生产者生产间隔时间为指明，此处将不设间隔时间（补充一点逻辑：若仓库中的产品少于20，则会唤醒生产者生产）
 *      2、消费者每隔3秒消费产品
 * @author: Administrator
 * @date: Created in 2020/11/16 18:40
 * @modified By :  guodf
 */
public class ProduceConsume {

    public static void main(String[] args) {
        //模拟存放产品的仓库
        SyncStack ss = new SyncStack();
        //新建生产者
        Producer p = new Producer(ss);
        //新建消费者
        Consume c = new Consume(ss);
        //新建生产者线程
        Thread tp = new Thread(p);
        //新建消费者线程
        Thread tc = new Thread(c);
        tp.start();
        tc.start();
    }
}

//产品类Product
class OpProduct{
    int id;//产品编号
    OpProduct(int id){
        this.id = id;
    }
    public String toString(){
        return "OpProduct:"+id;
    }
}

//装产品的仓库
class SyncStack{
    int index = 0;
    OpProduct[] stb = new OpProduct[20];

    //生产下产品并存入仓库
    public synchronized void push(OpProduct sb){
        //产品总数够20个时
        while(index==stb.length){
            try {
                //让当前线程等待
                this.wait();
                if(index<stb.length){
                    this.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //唤醒消费者线程
        this.notify();
        stb[index] = sb;
        this.index++;
    }

    //消费者消费产品
    public synchronized OpProduct pop(){
        //仓库没有产品，此时消费者等待，唤醒生产者
        while(index==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.notify();
        this.index--;
        return stb[index];
    }
}

//生产者类，实现了Runnable接口，以便于构造生产者线程
class Producer implements Runnable{
    SyncStack ss = null;
    Producer(SyncStack ss){
        this.ss = ss;
    }
    @Override
    public void run() {
        // 生产者开始生产产品
        for(int i=0;i<20;i++){
            OpProduct stb = new OpProduct(i);
            ss.push(stb);
            System.out.println("生产了"+stb);
        }
    }
}

//消费者类，实现了Runnable接口，以便于构造消费者线程
class Consume implements Runnable{
    SyncStack ss = null;
    public Consume(SyncStack ss) {
        super();
        this.ss = ss;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int i=0;i<20;i++){
            //开始消费
            OpProduct stb = ss.pop();
            System.out.println("消费了"+stb);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}