package jp.ryans.rssreader;

import java.net.URL;



public interface CatchResponse {

    /**
     * 通信タスクが完了すると呼び出されるコールバックメソッド
     * @param urlString 通信を行った時のURL
     * @param data その結果のデータを返却する。返却データは通信タスクに依存するのでインスタンスを検査してください
     */
    void setData(String urlString, Object data);

}
