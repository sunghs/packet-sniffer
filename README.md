# packet-sniffer
side project packet-sniffer

[블로그](https://sunghs.tistory.com/)

혼자하는 사이드 프로젝트 겸 인/아웃바운드 패킷을 스니핑 하는 프로그램입니다.

## 개발환경 (Dev Environment) - 2021. 12. 05 updated
- IDE : IntelliJ 2021.2.3
- JDK : Amazon-corretto-11
- SpringBoot : 2.6.0
- Gradle : 7.3

입니다.


## OS 별 sniff 확인점
### Windows
아래 링크의 Winpcap 을 설치해 줘야 합니다.  
https://www.winpcap.org/install/default.htm

### Unix, Linux, MacOS
권한을 찾을 수 없다는 에러가 발생하면 아래 명령어를 추가합니다.  
(You don't have permission to capture on that device ((cannot open BPF device) /dev/bpf0: Permission denied))
```
sudo chown <USER> /dev/bpf*
```
확장 BPF의 pcap 권한을 유저로 가져와야 합니다.

### 사용법
#### 자동 ip 선택 모드
application.yml 의 sniff.listen.ip 의 값을 패킷 스니핑 할 ip를 적고 start 하시면 됩니다.
#### 수동 ip 선택 모드
sniff.listen.auto-scan 의 값을 false 로 변경 후 구동 시 console 에 ip list를 보여줍니다.
