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
                System.out.println(downloadId + "���سɹ�");
            }
 
            @Override
            public void onStart(int downloadId, long fileSize) {
                System.out.println(downloadId + "��ʼ���أ��ļ���С��" + fileSize);
            }
 
            @Override
            public void onPublish(int downloadId, long size) {
                System.out.println("�����ļ�" + downloadId + "��С��" + size);
            }
 
            @Override
            public void onPause(int downloadId) {
                System.out.println("��ͣ����" + downloadId);
            }
 
            @Override
            public void onGoon(int downloadId, long localSize) {
                System.out.println("��������" + downloadId);
            }
 
            @Override
            public void onError(int downloadId) {
                System.out.println("���س���" + downloadId);
            }
 
            @Override
            public void onCancel(int downloadId) {
                System.out.println("ȡ������" + downloadId);
            }
        });
 
        d.start(false);
	}
}
