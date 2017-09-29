var RNIconBadge = require('react-native').NativeModules.RNIconBadge
var PushNotificationIOS = require('react-native').PushNotificationIOS
var Platform = require('react-native').Platform

module.exports = Platform.OS === 'ios' ? {
  setIconBadge: PushNotificationIOS.setApplicationIconBadgeNumber,
  getBadgeNumber: PushNotificationIOS.getApplicationIconBadgeNumber,
  clearBadge: PushNotificationIOS.setApplicationIconBadgeNumber(0),
} : RNIconBadge
