package et.tsingtaopad.custom;

import android.content.Context;
import android.view.View;

import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.plugin.common.common.utils.LogUtil;
import com.yuntongxun.plugin.im.dao.bean.RXConversation;
import com.yuntongxun.plugin.im.manager.port.OnBindViewHolderListener;
import com.yuntongxun.plugin.im.ui.chatting.model.BaseChattingRow;
import com.yuntongxun.plugin.im.ui.conversation.ConversationAdapter;


/**
 * Created with Android Studio IDEA.
 * <p>
 * This is the implementation of custom UI
 *
 * @author WJ
 * @version 1.0
 * @since 2017/8/21 9:53
 */

public class OnBindViewHolderListenerImpl implements OnBindViewHolderListener {
    private static final String TAG = LogUtil.getLogUtilsTag(OnBindViewHolderListenerImpl.class);


    @Override
    public BaseChattingRow onBindView(ECMessage ecMessage, BaseChattingRow baseChattingRow) {
        return null;
    }

    @Override
    public View onBindView(Context context, View view, RXConversation rxConversation, ConversationAdapter.BaseConversationViewHolder baseConversationViewHolder) {
        return null;
    }
}
