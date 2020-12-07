package chapter1.chapter1_4_catDogQueue;

import java.util.Queue;

/*
题目：实现一种猫狗队列的结构，类结构如下：
    //宠物类
    public class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return type;
        }
    }

    //猫
    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    //狗
    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

要求:1. 可以调用 add 方法将 Cat 类或 Dog 类的实例加入队列
    2. 可以调用 pollAll方法，将队列中所有的实例按进入队列的先后顺序依次弹出
    3. 可以调用 pollDog 方法，将队列中的 Dog 类实例按进入队列的先后顺序依次弹出
    4. 可以可调 pollCat 方法，将队列中的 Cat 类实例按进入队列的先后顺序依次弹出
    5. 可以调用 isEmpty 方法，检查队列中是否有实例元素
    6. 可以调用 isDogEmpty 方法，检查队列中是否有 Dog 类实例元素
    7. 可以调用 isCatEmpty 方法，检查队列中是否有 Cat 类实例元素
 */
/*
解题思路：
   使用两个队列分别保存 Dog、Cat实例，封装 Pet 方法，增加 index属性表示猫、狗入队列的顺序。
 */
public class CatDogAndQueue {

    //宠物类
    public class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return type;
        }
    }

    //猫
    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    //狗
    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    //封装Pet
    public class SuperPet {
        private Pet pet;
        private Long index;

        public SuperPet(Pet pet, Long index) {
            this.pet = pet;
            this.index = index;
        }

        public Pet getPet() {
            return pet;
        }

        public String getPetType() {
            return this.pet.getPetType();
        }

        public Long getIndex() {
            return this.index;
        }
    }

    public class CatDogQueue {
        private Queue<SuperPet> catQueue;
        private Queue<SuperPet> dogQueue;
        private Long index;

        public CatDogQueue(Queue<SuperPet> catQueue, Queue<SuperPet> dogQueue) {
            this.catQueue = catQueue;
            this.dogQueue = dogQueue;
            this.index = 0L;
        }

        //将猫或狗加入队列
        public void add(Pet pet) {
            String type = pet.getPetType();
            if ("cat".equalsIgnoreCase(type)) {
                SuperPet superPet = new SuperPet(pet, this.index++);
                this.catQueue.add(superPet);
            } else if ("dog".equalsIgnoreCase(type)) {
                SuperPet superPet = new SuperPet(pet, this.index++);
                this.dogQueue.add(superPet);
            }
        }

        //判断狗队列是否为空
        public boolean isDogEmpty() {
            return this.dogQueue.isEmpty();
        }

        //判断猫队列是否为空
        public boolean isCatEmpty() {
            return this.catQueue.isEmpty();
        }

        //判断队列是否为空
        public boolean isEmpty() {
            return this.dogQueue.isEmpty() && this.catQueue.isEmpty();
        }

        //弹出猫
        public Pet pollCat() {
            if (this.catQueue.isEmpty()) {
                return null;
            }
            SuperPet superPet = this.catQueue.poll();
            return superPet.getPet();
        }

        //弹出狗
        public Pet pollDog() {
            if (this.dogQueue.isEmpty()) {
                return null;
            }
            SuperPet superPet = this.dogQueue.poll();
            return superPet.getPet();
        }

        //弹出全部
        public Pet pollAll() {
            if (!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
                if (this.dogQueue.peek().getIndex() < this.catQueue.peek().getIndex()) {
                    return this.dogQueue.poll().getPet();
                } else {
                    return this.catQueue.poll().getPet();
                }
            } else if (!this.dogQueue.isEmpty()) {
                return this.dogQueue.poll().getPet();
            } else if (!this.catQueue.isEmpty()) {
                return this.catQueue.poll().getPet();
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {

    }
}
