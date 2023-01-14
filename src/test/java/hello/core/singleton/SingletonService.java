package hello.core.singleton;

public class SingletonService {
    //순서
    // 1. static 영역에 객체 instance를 미리 생성해 올료둔다.
    // 2. 이 객체 인스턴스가 필요하면 오직 getInstance를 통해서만 조회 할 수 있다.
    // 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    // 3. 딱 1개의 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new
    // 키워드로 객체인스턴스가 생성되는걸 막는다.

    //static을 사용하면 클래스 레벨로 올라가 하나만 존재 할 수 있다.
    //자기자신 생성후 instance의 참조에 넣어놓는다.
    private static final SingletonService instance=new SingletonService();

    public static SingletonService getInstance()
    {
        return instance;
    }


    //private을 통해 생성자를 만들어 외부에서 접근 할 수 없게한다.
    private SingletonService()
    {

    }

    public void login()
    {
        System.out.println("싱글통 객체 로직 호출");
    }


}

