package com.xialuo.service;

import com.xialuo.distrbutelock.ZookeeperDistrbuteLock;
import com.xialuo.lock.CustomLock;
import com.xialuo.util.NumberGenerator;

/**
 * @Auther: Mr.Kong
 * @Date: 2020/4/8 10:28
 * @Description:
 */
public class OrderService implements Runnable {

    private NumberGenerator numberGenerator = new NumberGenerator();

    private CustomLock customLock = new ZookeeperDistrbuteLock();

    @Override
    public void run() {
        getNumber();
    }

    public void getNumber() {
        try {
            customLock.getLock();
            String number = numberGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + "生成订单号：" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customLock.unLock();
        }
    }
}
