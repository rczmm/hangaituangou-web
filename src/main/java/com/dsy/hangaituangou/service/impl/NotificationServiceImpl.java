package com.dsy.hangaituangou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsy.hangaituangou.domain.Notification;
import com.dsy.hangaituangou.mapper.NotificationMapper;
import com.dsy.hangaituangou.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知服务实现类
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {

    /**
     * 根据租户ID查询通知列表
     * @param tenantId 租户ID
     * @return 通知列表
     */
    @Override
    public List<Notification> getNotificationsByTenantId(Long tenantId) {
        return list(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getTenantId, tenantId)
                .orderByDesc(Notification::getIsTop)
                .orderByDesc(Notification::getPriority)
                .orderByDesc(Notification::getCreateTime));
    }

    /**
     * 根据接收者ID查询通知列表
     * @param receiverId 接收者ID
     * @return 通知列表
     */
    @Override
    public List<Notification> getNotificationsByReceiverId(Long receiverId) {
        return list(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, receiverId)
                .orderByDesc(Notification::getIsTop)
                .orderByDesc(Notification::getPriority)
                .orderByDesc(Notification::getCreateTime));
    }

    /**
     * 创建新通知
     * @param notification 通知信息
     * @return 是否成功
     */
    @Override
    public boolean createNotification(Notification notification) {
        // 设置默认状态为未读
        if (notification.getStatus() == null) {
            notification.setStatus(0);
        }
        return save(notification);
    }

    /**
     * 更新通知状态
     * @param notificationId 通知ID
     * @param status 新状态
     * @return 是否成功
     */
    @Override
    public boolean updateNotificationStatus(Long notificationId, Integer status) {
        Notification notification = getById(notificationId);
        if (notification != null) {
            notification.setStatus(status);
            return updateById(notification);
        }
        return false;
    }

    /**
     * 获取未读通知数量
     * @param receiverId 接收者ID
     * @return 未读通知数量
     */
    @Override
    public int getUnreadNotificationCount(Long receiverId) {
        return (int) count(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, receiverId)
                .eq(Notification::getStatus, 0));
    }

    /**
     * 标记所有通知为已读
     * @param receiverId 接收者ID
     * @return 是否成功
     */
    @Override

    public boolean markAllNotificationsAsRead(Long receiverId) {
        List<Notification> notifications = list(new LambdaQueryWrapper<Notification>()
                .eq(Notification::getReceiverId, receiverId)
                .eq(Notification::getStatus, 0));
        
        if (notifications.isEmpty()) {
            return true;
        }
        
        for (Notification notification : notifications) {
            notification.setStatus(1);
        }
        
        return updateBatchById(notifications);
    }

    /**
     * 删除通知
     * @param notificationId 通知ID
     * @return 是否成功
     */
    @Override
    public boolean deleteNotification(Long notificationId) {
        return removeById(notificationId);
    }
}