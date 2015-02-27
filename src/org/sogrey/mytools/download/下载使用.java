class  
{
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		Download d = new Download(1, "http://baidu.com/test.zip",
                "/sdcard/download/");
        d.setOnDownloadListener(new Download.OnDownloadListener() {
            @Override
            public void onSuccess(int downloadId) {
                System.out.println(downloadId + "下载成功");
            }
 
            @Override
            public void onStart(int downloadId, long fileSize) {
                System.out.println(downloadId + "开始下载，文件大小：" + fileSize);
            }
 
            @Override
            public void onPublish(int downloadId, long size) {
                System.out.println("更新文件" + downloadId + "大小：" + size);
            }
 
            @Override
            public void onPause(int downloadId) {
                System.out.println("暂停下载" + downloadId);
            }
 
            @Override
            public void onGoon(int downloadId, long localSize) {
                System.out.println("继续下载" + downloadId);
            }
 
            @Override
            public void onError(int downloadId) {
                System.out.println("下载出错" + downloadId);
            }
 
            @Override
            public void onCancel(int downloadId) {
                System.out.println("取消下载" + downloadId);
            }
        });
 
        d.start(false);
	}
}
