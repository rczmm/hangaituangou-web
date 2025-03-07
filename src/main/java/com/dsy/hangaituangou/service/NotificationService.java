package com.dsy.hangaituangou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsy.hangaituangou.domain.Notification;

import java.util.List;

/**
 * 通知服务接口
 */
public interface NotificationService extends IService<Notification> {
    /**
     * 根据租户ID查询通知列表
     * @param tenantId 租户ID
     * @return 通知列表
     */
    List<Notification> getNotificationsByTenantId(Long tenantId);
    
    /**
     * 根据接收者ID查询通知列表
     * @param receiverId 接收者ID
     * @return 通知列表
     */
    List<Notification> getNotificationsByReceiverId(Long receiverId);
    
    /**
     * 创建新通知
     * @param notification 通知信息
     * @return 是否成功
     */
    boolean createNotification(Notification notification);
    
    /**
     * 更新通知状态
     * @param notificationId 通知ID
     * @param status 新状态
     * @return 是否成功
     */
    boolean updateNotificationStatus(Long notificationId, Integer status);
    
    /**
     * 获取未读通知数量
     * @param receiverId 接收者ID
     * @return 未读通知数量
     */
    int getUnreadNotificationCount(Long receiverId);
    
    /**
     * 标记所有通知为已读
     * @param receiverId 接收者ID
     * @return 是否成功
     */
    boolean markAllNotificationsAsRead(Long receiverId);
    
    /**
     * 删除通知
     * @param notificationId 通知ID
     * @return 是否成功
     */
    boolean deleteNotification(Long notificationId);
}