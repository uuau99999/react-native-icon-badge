var RNIconBadge = require('react-native').NativeModules.RNIconBadge
var PushNotificationIOS = require('react-native').PushNotificationIOS
var Platform = require('react-native').Platform

var iosBadgeModule = {}
iosBadgeModule.setIconBadge = PushNotificationIOS.setApplicationIconBadgeNumber
iosBadgeModule.getBadgeNumber =
  PushNotificationIOS.getApplicationIconBadgeNumber
iosBadgeModule.clearBadge = PushNotificationIOS.setApplicationIconBadgeNumber(0)

module.exports = Platform.OS === 'ios' ? iosBadgeModule : RNIconBadge
