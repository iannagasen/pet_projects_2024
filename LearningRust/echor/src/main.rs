use clap::{App, Arg};

fn main() {
  // this will be declare the output when running
  // cargo run -- -h

  let _matches = App::new("echor") // create the new App with the name echor
      .version("0.1.0") // use semantic version info
      .author("Ian Neil Agasen <iann.agasen@gmail.com>") // includ name and email address 
      .about("Rust echo") // short desc
      .arg(
        // Creates a new arg with name text
        // it is a required positional argument that must appear at least once and can be repeated
        Arg::with_name("text")
          .value_name("TEXT")
          .help("Input text")
          .required(true)
          .min_values(1)
      )
      .arg(
        // a flag that has only short name -n and takes no value
        Arg::with_name("omit_newline")
          .short("n") 
          .help("Do not print newline")
          .takes_value(false)
      )
      .get_matches();

  // only call unwrap()  if you know values will be there
  let text = _matches.value_of_lossy("text").unwrap();
  let omit_newline = _matches.is_present("omit_newline");

  let ending = 
    if omit_newline { "" } 
    else { "/n" } ;

  print!("{}{}", text.to_string(), ending);
}
