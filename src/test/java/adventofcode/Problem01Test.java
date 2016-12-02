package adventofcode;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem01Test {

    @Test
    public void should_be_2_for_R2() throws Exception {
        int blocks = Problem01.blocks(Arrays.asList(new Instruction("R2")));
        assertThat(blocks).isEqualTo(2);
    }

    @Test
    public void should_be_5_for_R2_L3() throws Exception {
        int blocks = Problem01.blocks(Arrays.asList(new Instruction("R2"), new Instruction("L3")));
        assertThat(blocks).isEqualTo(5);
    }

    @Test
    public void should_be_4_for_R2_R2() throws Exception {
        Instruction r2 = new Instruction("R2");
        int blocks = Problem01.blocks(Arrays.asList(r2, r2));
        assertThat(blocks).isEqualTo(4);
    }

    @Test
    public void should_be_1_for_R2_R2_R3() throws Exception {
        Instruction r2 = new Instruction("R2");
        int blocks = Problem01.blocks(Arrays.asList(r2, r2, new Instruction("R3")));
        assertThat(blocks).isEqualTo(3);
    }

    @Test
    public void should_be_12_when_R5_L5_R5_R3() throws Exception {
        int blocks = Problem01.blocks(Arrays.asList(new Instruction("R5"), new Instruction("L5"), new Instruction("R5"), new Instruction("R3")));
        assertThat(blocks).isEqualTo(12);
    }


    @Test
    public void part1_solution_input1() throws Exception {
        List<String> list = Files.readAllLines(Paths.get("src", "test", "resources", "problem01.txt"));
        List<Instruction> instructions = Arrays.stream(list.get(0).split(",")).map(String::trim).map(Instruction::new).collect(Collectors.toList());

        int blocks = Problem01.blocks(instructions);
        System.out.println(blocks);
        assertThat(blocks).isEqualTo(298);

    }

    @Test
    public void part1_solution_input2() throws Exception {
        List<String> list = Files.readAllLines(Paths.get("src", "test", "resources", "problem_011.txt"));
        List<Instruction> instructions = Arrays.stream(list.get(0).split(",")).map(String::trim).map(Instruction::new).collect(Collectors.toList());
        int blocks = Problem01.blocks(instructions);
        System.out.println(blocks);
        assertThat(blocks).isEqualTo(239);
    }

    @Test
    public void part2_solution_input2() throws Exception {
        List<String> list = Files.readAllLines(Paths.get("src", "test", "resources", "problem_011.txt"));
        List<Instruction> instructions = Arrays.stream(list.get(0).split(",")).map(String::trim).map(Instruction::new).collect(Collectors.toList());
        int blocks = Problem01.blocks(instructions);
    }

    @Test
    public void part2_solution_input1() throws Exception {
        List<String> list = Files.readAllLines(Paths.get("src", "test", "resources", "problem01.txt"));
        List<Instruction> instructions = Arrays.stream(list.get(0).split(",")).map(String::trim).map(Instruction::new).collect(Collectors.toList());
        int blocks = Problem01.blocks(instructions);
    }

    @Test
    public void part2() throws Exception {
        int blocks = Problem01.blocks(Arrays.asList(new Instruction("R8"), new Instruction("R4"), new Instruction("R4"), new Instruction("R8")));
    }

    @Test
    public void turn_to_0() throws Exception {

        Instruction r2 = new Instruction("R2");
        int blocks = Problem01.blocks(Arrays.asList(r2, r2, r2, r2));
        assertThat(blocks).isEqualTo(0);
    }
}