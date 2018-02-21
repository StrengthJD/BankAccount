package com.exercise;

public class BankAccount {
	private long number;
	private long balance;
	private Action[] actions;
	private Object history;
	
	public BankAccount() {
		actions = new Action[10];
	}
	
	public class Action {
		private String act;
		private long amount;
		
		public Action(String act, long amount) {
			
			this.act = act;
			this.amount = amount;
		}
		
		@Override
		public String toString() {
			return act + amount;
		}
	}
	
	public void deposit(int amount) {
		balance += amount;
		recordAction(new Action("deposit", amount));

	}
	
	public void withdraw(int amount) {
		balance -= amount;
		recordAction(new Action("deposit", amount));

	}
	
	public void transfer(BankAccount other, int amount) {
		other.withdraw(amount);
		deposit(amount);
		recordAction(new Action("transfer", amount));
		other.recordAction(new Action("transfer", amount));
	}
	
	private void recordAction(Action action) {
		int slot = findFreeSlot();
		
		if(slot < 0)
			slot = createSlot();
		
		actions[slot] = action;
	}
	
	private int createSlot() {
		for (int i = 0; i < actions.length; i++) {
			actions[i] = actions[i + 1];
		}
		return actions.length - 1;
	}

	private int findFreeSlot() {
		for (int i = 0; i < actions.length; i++) {
			if(actions[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private Object getHistory() {
		return history;

	}
	
	
}
