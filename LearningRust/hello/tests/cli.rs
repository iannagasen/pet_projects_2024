use assert_cmd::Command;

#[test] // they call it an attribute in Rust, annotation in java
fn works() {
  // the assert! macro assert that a Boolean expression is true
  assert!(true)
}

#[test]
fn runs() {
  // Command::cargo::bin - create a Command to run hello in the current crate.
  let mut cmd = Command::cargo_bin("hello").unwrap();
  cmd.assert().success().stdout("Hello, world!\n");
}

#[test]
fn true_ok() {
  let mut cmd = Command::cargo_bin("true").unwrap();
  cmd.assert().success();
}

#[test]
fn false_not_ok () {
  let mut cmd = Command::cargo_bin("false").unwrap();
  cmd.assert().failure();
}

