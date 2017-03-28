SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `graph_app` DEFAULT CHARACTER SET utf8 ;
USE `graph_app` ;

-- -----------------------------------------------------
-- Table `graph_app`.`graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graph_app`.`graph` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graph_app`.`vertex`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graph_app`.`vertex` (
  `id` INT NOT NULL,
  `graph_id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `vertex_graph_fk_idx` (`graph_id` ASC),
  CONSTRAINT `vertex_graph_fk`
    FOREIGN KEY (`graph_id`)
    REFERENCES `graph_app`.`graph` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graph_app`.`edge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graph_app`.`edge` (
  `id` INT NOT NULL,
  `vertex_src_id` INT NOT NULL,
  `vertex_dst_id` INT NOT NULL,
  `weight` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `edge_vertex_src_fk_idx` (`vertex_src_id` ASC),
  INDEX `edge_vertex_dst_fk_idx` (`vertex_dst_id` ASC),
  UNIQUE INDEX `edge_uk` (`vertex_src_id` ASC, `vertex_dst_id` ASC) ,
  CONSTRAINT `edge_vertex_src_fk`
    FOREIGN KEY (`vertex_src_id`)
    REFERENCES `graph_app`.`vertex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `edge_vertex_dst_fk`
    FOREIGN KEY (`vertex_dst_id`)
    REFERENCES `graph_app`.`vertex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `graph_app`.`test_case`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graph_app`.`test_case` (
  `id` INT NOT NULL,
  `target` VARCHAR(50) NOT NULL,
  `width` INT NOT NULL,
  `length` INT NOT NULL,
  `height` INT NOT NULL,
  `weight` DOUBLE NOT NULL,
  `expected_cost` DOUBLE NULL,
  `graph_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `test_case_graph_fk_idx` (`graph_id` ASC),
  CONSTRAINT `test_case_graph_fk`
    FOREIGN KEY (`graph_id`)
    REFERENCES `graph_app`.`graph` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;