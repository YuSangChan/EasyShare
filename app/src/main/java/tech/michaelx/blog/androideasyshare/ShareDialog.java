package tech.michaelx.blog.androideasyshare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import blog.michaelx.tech.IShareDialog;
import blog.michaelx.tech.callback.OnShareClickListener;
import blog.michaelx.tech.platform.TenchentQQShare;
import blog.michaelx.tech.platform.WechatMomentsShare;
import blog.michaelx.tech.platform.WechatShare;
import blog.michaelx.tech.platform.WeiboShare;

/**
 * 分享选择弹窗
 *
 * @author MichaelX
 * @version 1.0
 * @since 2019/4/3
 */
public class ShareDialog extends AlertDialog implements IShareDialog {
    private static final String[] SHARE_ITEMS = {"微信", "朋友圈", "QQ", "微博"};
    private OnShareClickListener listener;

    public ShareDialog(Context context) {
        super(context);
        build(context);
    }

    private void build(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("分享至：")
                .setSingleChoiceItems(SHARE_ITEMS, 0, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                onClickWechat();
                                break;

                            case 1:
                                onClickWechatMoments();
                                break;

                            case 2:
                                onClickQQ();
                                break;

                            case 3:
                                onClickWeibo();
                                break;

                            default:
                                break;
                        }
                    }
                }).create();

    }

    /**
     * 点击了微信
     */
    public void onClickWechat() {
        Toast.makeText(getContext(), "分享至微信好友", Toast.LENGTH_LONG).show();
        listener.onShareClick(new WechatShare());
    }

    /**
     * 点击了朋友圈
     */
    public void onClickWechatMoments() {
        Toast.makeText(getContext(), "分享至微信朋友圈", Toast.LENGTH_LONG).show();
        listener.onShareClick(new WechatMomentsShare());
    }

    /**
     * 点击了QQ分享
     */
    public void onClickQQ() {
        Toast.makeText(getContext(), "分享至QQ朋友", Toast.LENGTH_LONG).show();
        listener.onShareClick(new TenchentQQShare());
    }

    /**
     * 点击了微博分享
     */
    public void onClickWeibo() {
        Toast.makeText(getContext(), "分享至微博", Toast.LENGTH_LONG).show();
        listener.onShareClick(new WeiboShare());
    }

    @Override
    public <T extends IShareDialog> T setOnShareClickListener(OnShareClickListener listener) {
        this.listener = listener;
        return (T) this;
    }
}
