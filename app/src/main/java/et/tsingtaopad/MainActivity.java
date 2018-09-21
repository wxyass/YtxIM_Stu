package et.tsingtaopad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.io.InvalidClassException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initIMSDK();
    }

    private void initIMSDK() {
        //判断SDK是否已经初始化
        if(!ECDevice.isInitialized()) {
            /*  initial: ECSDK 初始化接口
            * 参数：
            *     inContext - Android应用上下文对象
            *     inListener - SDK初始化结果回调接口，ECDevice.InitListener
            *
            * 说明：示例在应用程序创建时初始化 SDK引用的是Application的上下文，
            *       开发者可根据开发需要调整。
            */
            ECDevice.initial(getApplicationContext(), new ECDevice.InitListener() {
                @Override
                public void onInitialized() {
                    // SDK已经初始化成功
                    Log.i("","初始化SDK成功");
                    //设置登录参数，可分为自定义方式和VoIP验密方式，详情点此查看>>
                    ECInitParams params = ECInitParams.createParams();
                    params.setUserid("13264303170");
                    params.setAppKey("8aaf070865e6b6eb0165f0f949f2085a");
                    params.setToken("dc3591b2fe5243be9d9a622e9244bbeb");
                    //设置登陆验证模式：自定义登录方式
                    params.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
                    //LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO。使用方式详见注意事项）
                    params.setMode(ECInitParams.LoginMode.FORCE_LOGIN);


                    //设置登录回调监听
                    ECDevice.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener() {
                        public void onConnect() {
                            //兼容旧版本的方法，不必处理
                        }

                        @Override
                        public void onDisconnect(ECError error) {
                            //兼容旧版本的方法，不必处理
                        }

                        @Override
                        public void onConnectState(ECDevice.ECConnectState state, ECError error) {
                            if(state == ECDevice.ECConnectState.CONNECT_FAILED ){
                                if(error.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
                                    Log.i("","==帐号异地登陆");
                                }
                                else
                                {
                                    Log.i("","==其他登录失败,错误码："+ error.errorCode);
                                }
                                return ;
                            }
                            else if(state == ECDevice.ECConnectState.CONNECT_SUCCESS) {
                                Log.i("","==登陆成功");
                            }
                        }
                    });

                    //IM接收消息监听，使用IM功能的开发者需要设置。
                    ECDevice.setOnChatReceiveListener(new OnChatReceiveListener() {
                        @Override
                        public void OnReceivedMessage(ECMessage msg) {
                            Log.i("","==收到新消息");
                        }

                        @Override
                        public void onReceiveMessageNotify(ECMessageNotify ecMessageNotify) {

                        }

                        @Override
                        public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage notice) {
                            //收到群组通知消息,可以根据ECGroupNoticeMessage.ECGroupMessageType类型区分不同消息类型
                            Log.i("","==收到群组通知消息（有人加入、退出...）");
                        }

                        @Override
                        public void onOfflineMessageCount(int count) {
                            // 登陆成功之后SDK回调该接口通知帐号离线消息数
                        }

                        @Override
                        public int onGetOfflineMessage() {
                            return 0;
                        }

                        @Override
                        public void onReceiveOfflineMessage(List msgs) {
                            // SDK根据应用设置的离线消息拉取规则通知应用离线消息
                        }

                        @Override
                        public void onReceiveOfflineMessageCompletion() {
                            // SDK通知应用离线消息拉取完成
                        }

                        @Override
                        public void onServicePersonVersion(int version) {
                            // SDK通知应用当前帐号的个人信息版本号
                        }

                        @Override
                        public void onReceiveDeskMessage(ECMessage ecMessage) {

                        }

                        @Override
                        public void onSoftVersion(String s, int i) {

                        }
                    });


                    //验证参数是否正确
                    if(params.validate()) {
                        // 登录函数
                        ECDevice.login(params);
                    }

                    ECDevice.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener() {
                        @Override
                        public void onConnect() {

                        }

                        @Override
                        public void onDisconnect(ECError ecError) {

                        }

                        @Override
                        public void onConnectState(ECDevice.ECConnectState ecConnectState, ECError ecError) {
                            if(ecError.errorCode == 200) {

                            }

                            //        IMChattingHelper.getInstance().getPersonInfo();


                        }
                    });

                }
                @Override
                public void onError(Exception exception) {
                    //在初始化错误的方法中打印错误原因
                    Log.i("","初始化SDK失败"+exception.getMessage());
                }
            });
        }
        // 已经初始化成功，后续开发业务代码。
        Log.i(TAG, "初始化SDK及登陆代码完成");
    }


    public static String getNameById(String id) {
        return "名字" + id;
    }
}
