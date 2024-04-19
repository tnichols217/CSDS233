{
  description = "Dev shell";
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    gitignore = {
      url = "github:hercules-ci/gitignore.nix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
    mavenix = {
      url = "github:nix-community/mavenix";
      inputs.nixpkgs.follows = "nixpkgs";
    };
  };

  outputs = { self, nixpkgs, flake-utils, gitignore, mavenix }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        system = "x86_64-linux";
        pkgs = import nixpkgs {inherit system;};
        mv = pkgs.callPackage mavenix {};
        args = {inherit system; mavenix = mv; jdk = pkgs.jdk20; };
        buildMv = p: mv.buildMaven{ src=p; doCheck = false; infoFile = p+"/mavenix.lock"; maven = pkgs.maven.overrideAttrs (_: { jdk = pkgs.jdk20; }); };
      in with pkgs ; {
        packages = rec {
          P1 = buildMv ./P1;
          P2 = buildMv ./P2;
          P3 = buildMv ./P3;
          P4 = buildMv ./P4;
          default = P3;
        };
        devShells = rec {
          java = pkgs.mkShell {
            nativeBuildInputs = [
            ];
            buildInputs = [
              jdk20
              maven
              mavenix.packages.${system}.mavenix-cli
            ];
          };
          default = java;
        };
      });
}