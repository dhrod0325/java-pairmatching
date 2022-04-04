package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;

import java.util.stream.Collectors;

public class InputView {
    public static String inputMenu() {
        System.out.println();
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");

        return Console.readLine();
    }

    public static String inputMatching() {
        StringBuilder sb = new StringBuilder();
        sb.append("#########################################\n");
        sb.append("과정 : ").append(getCourses()).append("\n");
        sb.append("미션 : \n");

        Level.list().forEach(level -> {
            sb.append("- ").append(level.getName()).append(" : ");
            sb.append(String.join(" | ", Mission.getNamesByLevel(level))).append("\n");
        });

        sb.append("#########################################\n");
        sb.append("과정, 레벨, 미션을 선택하세요.");

        System.out.println(sb);

        return Console.readLine();
    }

    public static String inputReMatching() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        return Console.readLine();
    }

    private static String getCourses() {
        return Course.list().stream()
                .map(Course::getName)
                .collect(Collectors.joining(" | "));
    }
}
