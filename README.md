# packet-sniffer
side project packet-sniffer

[블로그](https://sunghs.tistory.com/)

혼자하는 사이드 프로젝트 겸 인/아웃바운드 패킷을 스니핑 하는 프로그램입니다.

## 개발환경 (Dev Environment)
- IDE : IntelliJ 2020.01
- JDK : Amazon-corretto-11
- SpringBoot : 2.3.2
- Gradle : 6.4.1

입니다.


## OS 별 sniff 확인점
### Windows
아래 링크의 Winpcap 을 설치해 줘야 합니다.  
https://www.winpcap.org/install/default.htm

### Unix, Linux, MacOS
권한을 찾을 수 없다는 에러가 발생하면 아래 명령어를 추가합니다.  
```
sudo chown <USER> /dev/bpf*
```
pcap 권한을 유저로 가져와야 합니다.  
